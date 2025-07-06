# ✅ Taskora Backend

## 📖 Overview

This project is a Homemade Jira.  
It is designed to achieve some basic task about project management.  
Built with SpringBoot Gradle Kotlin stack, it aims to practice some IT skills such as Infra/DevOps.

## 🚀 Getting Started (Run Locally)

Follow these steps to run the project on your local machine:

### Prerequisites

- TODO

### Installation

```bash
# Clone the repository
git clone git@github.com:projex-io/taskora-back.git
or
git clone https://github.com/projex-io/taskora-back.git

# Navigate into the project folder
cd taskora-back

# Start the project
docker-compose up -d
run on IntelliJ
```

## Add a contribution
### Create a new work branch
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


```bash
git commit -m "Your commit message #someLabel #no-draft"
git push
```
List of possible label : 
- auto-merge (if PR has auto-merge enable it's will merge automatically if all check are passed)
- breaking-change
- bug
- documentation
- feature
- no-draft (create the PR with no-draft status or make an existing PR ready-for-review)
- performance
- question
- refactor
- release

List of alias label :
- fast => no-draft + auto-merge

A pull request will automatically create on push new branch with appropriate configuration

### On merge Pull Request
when a PR is successfully merge, workflows create automatically :

- A draft release with the changelog
- A docker image with tag dev-yyyymmjj-sha::7

### Make a release
```bash
make release(-minor|-major)
```
A release with appropriate bump version will be created and the current draft is deleted. 

JUSTATEST