#!/bin/bash
#
# commit-changes.sh
#
# It commits git repository changes automating commit messages 
#   using version and description from README.md
#
# PROJECT: Robins10
# AUTHOR: David Alberto Díaz Torres
# MAIL: robins10@mail.com
# VERSION: 0.1.0
# CREATED: 22-10-2019
# UPDATED: 27-03-2020
# CHANGELOG:
#   22-10-2019 - 0.0.3:
#     Allows blank (empty) lines in README.md
#     Make a commit in another repository
#     Get commit message from Changelog
#     Add new BUILD number in README.md before commit
#     Add new VERSION to another files like setup.py and __init__.py before commit
#   27-03-2020 - 0.1.0:
#     Get as the new VERSION, the last User Story/Improvement/Patch version 
# LINK: robins10.com
#

GIT_ORG="origin-git"

sed -i '/^$/d' README.md
msg=$(grep -A2 "Changelog" README.md | awk 'NR==3{print $0}' | sed 's/^[-* 0-9\.:]\+//g')
version=$(grep -A2 "Changelog" README.md | awk 'NR==3{print $0}' | awk '{printf "%s", $2}' | sed 's/:$//g')
build=$(awk -F '`' 'NR==2{print $2}' README.md | cut -d'-' -f2)
((build += 1))
sed -i "2s/[0-9\.\-]\+/$version-$build/g" README.md

###### UNCOMMENT to add the version code into Python projects: ###########################
#sed -i "2s/version = \"[0-9\.\-]\+\"/version = \"$version-$build\"/g" iothub/__init__.py
#sed -i "s/version=\"[0-9\.]\+\"/version=\"$version\"/g" setup.py
##########################################################################################

echo -e "\nCommit changes for \"$version-$build: $msg\""
git add .
git commit -m "$version-$build: $msg"

echo -e "\nPushing into remote repository..."
git push -u $GIT_ORG master
echo
git log -n 2 --oneline
