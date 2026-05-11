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
    df = pd.read_csv('data/addresses.csv')  
    df.to_sql('addresses', db, if_exists= 'replace', dtype= {'official_id': 'INTEGER', 'official_type': 'STRING', 'address': 'STRING'})
    print('addresses have been populated!')

    df = pd.read_csv('data/committees.csv')
    #should include 'DESC' later on
    df = df[['name','official_type']]
    df.to_sql('committee', db, if_exists= 'replace', dtype= {'official_type': 'STRING', 'name': 'STRING'})
    print('committees have been populated!')

    #populate officials
    db.execute("DELETE FROM officials")
    for i in range(0, 3):
        elemname = officials[i]
        df = pd.read_csv('data/' + elemname + '.csv')
        df_list = df[['district_id', 'committees']].copy()
        df_list = df_list.rename(columns={'district_id': elemname + "_id"})

        #add non-list columns
        df = df[['name', 'party', 'salary','district_id', 'official_type']]
        df = df[df['name'].str.lower()  != 'vacant']
        df.to_sql('officials', db, if_exists='append', dtype= {'name': 'STRING', 'PARTY': 'STRING', 'salary': 'INTEGER'}, index=False)

        #add list colums
        df_list = df_list.dropna(subset=['committees'])
        df_list['committees'] = df_list['committees'].astype(str).str.split()
        df_list = df_list.explode('committees')
        df_list.to_sql(elemname + '_committee_ids', db, if_exists= 'append', index=False)
        print(elemname  + ' have been populated!')
    
    #populate districts
    db.execute("DELETE FROM districts")
    for i in range(1, 3):
        elemname = districts[i]
        elemConfig = districts[i]
        df = pd.read_csv('data/' + elemname + '.csv')
        df_list = df[['district_id', 'counties']].copy()

        #add non-list columns
        df = df[['map_type','dtype','district_id']]
        df.to_sql('districts', db, if_exists='append', dtype= {'map_type': 'STRING', 'dtype': 'STRING'}, index=False)

        #add list colums
        df_list['counties'] = df_list['counties'].astype(str).str.split()
        df_list = df_list.explode('counties')
        df_list.to_sql('district_counties', db, if_exists= 'append', index= False)
        print(elemname + ' have been populated!')

except Exception as e:
    print(f'Something went wrong: {e}')
