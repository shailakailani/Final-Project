# CalOfficials
## Authors
Shaila Lewis and Kelvin Myat

## Description
This application is meant to allow users in California to easily find out who their elected officials and representatives are. Users can search for officials by name, district or county. Users can also post comments or questions on the comments page.

# Project Structure
The workspace contains:
- `calofficials/src`: folder to maintain sources
    - `main`:
        - `java`: folder to maintain classes and entrypoints organized.
            - `api/controller`: folder containing controllers that connect URLs to pages and features.
            - `api/service`: folder containing service classes for search and comment logic.
            - `api/dto`: folder containing DTO classes used to display official information.
            - `shai/kelv/calofficials/calgov`: folder containing government official entities and repositories.
            - `shai/kelv/calofficials/comments`: folder containing comment entity and repository files.
        - `resources`: folder to maintain visuals and web templates organized.
            - `static`: folder containing CSS files for page styling.
            - `templates`: folder containing HTML pages for the website.
            - `templates/fragments`: folder containing reusable HTML fragments such as the navigation menu.
- `data`: folder to maintain csvs containing initial data for our government information.
- `doc`: folder maintaining documentation such as the UML diagram and website preview images.

## Important Files
- `CalOfficialsApplication.java`: main file used to run the Spring Boot application.
- `SearchPageController.java`: controls the search page.
- `CountyPageController.java`: controls the county page.
- `CommentsPageController.java`: controls the comments page and handles comment form submissions.
- `SearchService.java`: contains logic for searching officials by name, district or county.
- `CommentService.java`: contains logic for saving and loading comments.
- `Comment.java`: represents a comment stored in the comments database table.
- `CommentRepository.java`: connects the comment feature to the database.
- `OfficialDTO.java`: stores official information that is displayed on the website.

## Classes (UML Diagram)
<img src="doc/CalOfficials.png">

## Website Mockup
### Home Page
The home page introduces the website and provides navigation links to the other pages.

<img src="doc/homepage.png">

### County Page
The county page allows users to search for officials by California county.

<img src="doc/countypage.png">

### Search Page
The search page allows users to search for officials by name or district.

<img src="doc/searchpage.png">

### Comments Page
The comments page allows users to enter a username and message. If the username is left blank, the comment will be posted as anonymous. Posted comments are saved and displayed on the page.

<img src="doc/commentspage.png">

# Instructions
Run CalOfficialsApplication.java in the shai/kelv/calofficials folder then enter localhost:8080 in your browser to access the website. Follow the instructions in the website after that.

## Website Routes
- Home: `localhost:8080`
- Search: `localhost:8080/search`
- County: `localhost:8080/county`
- Comments: `localhost:8080/comments`
