#!/bin/bash

# Check if message is provided
if [ -z "$1" ]; then
  echo "❌ Error: Please provide a commit message."
  echo "Usage: ./gitpush.sh \"Your commit message here\""
  exit 1
fi

# Git operations
echo "📦 Staging files..."
git add .

echo "📝 Committing..."
git commit -m "$1"

echo "🔄 Pulling latest changes..."
git pull origin master --rebase

echo "🚀 Pushing to GitHub..."
git push origin master

echo "✅ Done!"
