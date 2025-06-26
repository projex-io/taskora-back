#!/bin/bash

set -e

CLEAN=false

print_help() {
  echo ""
  echo "🚀 Script de lancement Docker Compose"
  echo ""
  echo "Utilisation : ./launch.sh [options]"
  echo ""
  echo "Options :"
  echo "  -c, --clean     Supprime aussi les volumes Docker (reset complet)"
  echo "  -h, --help      Affiche cette aide"
  echo ""
  echo "Par défaut, les volumes sont conservés."
}

# Analyse des options
case "$1" in
  -c|--clean)
    CLEAN=true
    ;;
  -h|--help)
    print_help
    exit 0
    ;;
  "" )
    # Pas d'option = comportement par défaut
    ;;
  *)
    echo "❌ Option inconnue : $1"
    print_help
    exit 1
    ;;
esac

# Stop et (éventuellement) clean
echo "🛑 Arrêt des conteneurs..."
if [ "$CLEAN" = true ]; then
  echo "🗑 Suppression des volumes activée (--clean)"
  docker compose down --volumes --remove-orphans > /dev/null 2>&1
else
  docker compose down --remove-orphans > /dev/null 2>&1
fi

# Rebuild
echo "🛠 Rebuild des images sans cache..."
docker compose build --no-cache > /dev/null 2>&1

# Lancement
echo "🚀 Lancement des services en mode détaché..."
docker compose up -d

echo -n "✅ Application lancée"
[ "$CLEAN" = true ] && echo " (volumes supprimés)" || echo ""
