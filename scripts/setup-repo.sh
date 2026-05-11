#!/bin/bash
# Script to create the remote repository and push
# Run this after creating the GitHub repo

set -e

TOKEN="${1:-"$GITHUB_TOKEN"}"
if [ -z "$TOKEN" ]; then
    echo "Usage: $0 <github_token>"
    echo "Or set GITHUB_TOKEN environment variable"
    exit 1
fi

echo "Creating repository hermes-ai-android on GitHub..."
curl -s -X POST \
  -H "Authorization: Bearer $TOKEN" \
  -H "Accept: application/vnd.github+json" \
  https://api.github.com/user/repos \
  -d '{"name":"hermes-ai-android","description":"Hermes AI Android app powered by DeepSeek","private":false}'

echo ""
echo "Pushing code..."
git push -u origin main
echo "Done! Repository available at: https://github.com/nesterovkos-arch/hermes-ai-android"
