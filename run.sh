#!/bin/bash

params=""
params+="-Dgit-directory=../"

mvn io.github.gitmessagehook:git-message-hook-maven-plugin:1.0.0-SNAPSHOT:commit-message #"$params"
