#!/bin/bash

echo "🔄 Pulling latest changes from GitHub..."

# Pull with rebase to keep history clean
git pull origin master --rebase

if [ $? -eq 0 ]; then
  echo "✅ Successfully pulled latest updates!"
else
  echo "❌ Something went wrong. Please check for conflicts."
fi
