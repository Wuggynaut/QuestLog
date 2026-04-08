# QuestLog
CRUD application designed for tracking played TTRPG sessions.

# QuestLog

QuestLog is a CRUD application designed for tabletop role-playing gamers who want to track their play habits. It allows users to log the RPG systems they play, organize their campaigns, and record individual game sessions — making it easy to look back and see how many sessions of each game they played or ran as Game Master over a given period.

## Core Features

### Public View (Visible to all visitors)

- Browse a catalogue of tabletop RPG systems
- View game details such as description and genre

### Logged-in User Features (USER role)

- Create campaigns and link them to a specific game
- Log individual game sessions within a campaign, recording the date, duration, personal notes, and whether the user participated as a player or GM
- Track one-shot games by creating a single-session campaign
- Edit and delete personal campaigns and sessions

### Administrator Features (ADMIN role)

- Add, edit, and delete games in the catalogue
- Manage user accounts

## Database Structure

| Table    | Attributes                                                             |
|----------|------------------------------------------------------------------------|
| User     | id, username, password, role (USER/ADMIN)                              |
| Game     | id, title, description, genre (string), image URL, year published      |
| Campaign | id, name, description, game (FK), user (FK), status (ACTIVE/COMPLETED) |
| Session  | id, campaign (FK), date, duration in minutes, notes, role (PLAYER/GM)  |
| Genre    | id, name, games (List)                                                 |
