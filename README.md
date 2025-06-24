# ✅ Taskora Backend

## 📖 Overview

This project is a [brief description of your service/project].  
It is designed to [explain the purpose: solve a problem, provide an API, serve a UI, etc.].  
Built with [tech stack / framework], it aims to [short objective or goal].

## 🚀 Getting Started (Run Locally)

Follow these steps to run the project on your local machine:

### Prerequisites

- [e.g., Node.js, Docker, Python 3.11, etc.]
- [Other dependencies]

### Installation

```bash
# Clone the repository
git clone https://github.com/your-username/your-repo-name.git

# Navigate into the project folder
cd your-repo-name

# Install dependencies
[command to install deps, e.g., npm install or pip install -r requirements.txt]

# Start the project
[command to run the project locally, e.g., npm run dev or docker-compose up]
```

## Add a contribution
```bash
git checkout -b prefix/your-feature-name
```
PREFIX="feat"     automatically add => LABEL="feature"

PREFIX="bug"      automatically add => LABEL="bug"

PREFIX="docs";     automatically add => LABEL="documentation"

PREFIX="refactor"; automatically add =>  LABEL="refactor" 

PREFIX="perfs";    automatically add =>  LABEL="performance" 

PREFIX="rel";      automatically add => LABEL="release" 

PREFIX="";         automatically add =>  LABEL="" 

A pull request will automatically create on push new branch with appropriate configuration

```bash
make release(-minor|-major)
```