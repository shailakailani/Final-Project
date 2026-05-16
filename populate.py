#DESC: file to initially populate db 
import pandas as pd;
import sqlite3 as sql3;

officials = ['assembly_member',
             'senator',
             'congress_member'
            ]
districts = [
    'senate_map',
    'congress_map',
    'assembly_map'
]
db = sql3.connect('calgov.db')

try:
    offset = 1 
    df = pd.read_csv('data/addresses.csv')  
    df.to_sql('addresses', db, if_exists= 'replace', dtype= {'official_id': 'INTEGER', 'official_type': 'STRING', 'address': 'STRING'})
    print('addresses have been populated!')

    df = pd.read_csv('data/committees.csv')
    #should include 'DESC' later on
    df = df[['name','official_type','id']]
    df.to_sql('committee', db, if_exists= 'replace', dtype= {'official_type': 'STRING', 'name': 'STRING'})
    print('committees have been populated!')

    #populate officials
    db.execute("DELETE FROM officials")
    db.execute("DROP TABLE IF EXISTS official_committee_ids")
    for i in range(0, 3):
        elemname = officials[i]
        df = pd.read_csv('data/' + elemname + '.csv')
        df_list = df[['district_id', 'committees', 'official_type']].copy()
        df_list = df_list.rename(columns={'district_id':'official_id','committees': "committee_id"})

        #add non-list columns
        df = df[['name', 'party', 'salary','district_id', 'official_type']]
        df = df[df['name'].str.lower()  != 'vacant']
        df['id'] = df.index + offset
        offset += len(df)
        df = df.reset_index(drop=True)

        df.to_sql('officials', db, if_exists='append', dtype= {'name': 'STRING', 'PARTY': 'STRING', 'salary': 'INTEGER'}, index=False)

        #add list colums
        df_list = df_list.dropna(subset=['committee_id'])
        df_list['committee_id'] = df_list['committee_id'].astype(str).str.replace(',', '').str.split()
        df_list = df_list.explode('committee_id')
        df_list.to_sql('official_committee_ids', db, if_exists='append', index=False)
        print(elemname  + ' have been populated!')
    
    #populate districts
    db.execute("DROP TABLE IF EXISTS districts")
    db.execute("DROP TABLE IF EXISTS district_counties")
    for i in range(0, 3):
        elemname = districts[i]
        elemConfig = districts[i]
        df = pd.read_csv('data/' + elemname + '.csv')
        df_list = df[['district_id', 'counties']].copy()
        df_list['map_type'] = elemname.replace('_map','').upper()

        #add non-list columns
        df = df[['map_type','dtype','district_id']]
        df.to_sql('districts', db, if_exists='append', dtype= {'map_type': 'STRING', 'dtype': 'STRING'}, index=False)

        #add list colums
        df_list['counties'] = df_list['counties'].astype(str).str.replace(" ","").str.split(",")
        df_list = df_list.explode('counties')
        df_list.to_sql('district_counties', db, if_exists= 'append', index= False)
        print(elemname + ' have been populated!')

except Exception as e:
    print(f'Something went wrong: {e}')
