SHELL := /bin/bash

release: release-patch

release-patch:
	@$(MAKE) bump TYPE=patch

release-minor:
	@$(MAKE) bump TYPE=minor

release-major:
	@$(MAKE) bump TYPE=major

bump:
	@echo "Fetching remote tags..."
	@git fetch --tags
	@LAST_TAG=$$(git fetch --tags && git tag --sort=-v:refname | head -n 1 || echo "v0.0.0"); \
	echo "Last tag: $$LAST_TAG"; \
	VERSION=$$(echo $$LAST_TAG | sed 's/^v//'); \
	IFS='.' read -r MAJOR MINOR PATCH <<< "$$VERSION"; \
	case "$(TYPE)" in \
		major) MAJOR=$$((MAJOR+1)); MINOR=0; PATCH=0 ;; \
		minor) MINOR=$$((MINOR+1)); PATCH=0 ;; \
		patch) PATCH=$$((PATCH+1)) ;; \
	esac; \
	NEW_TAG="v$$MAJOR.$$MINOR.$$PATCH"; \
	echo "✅ New tag would be: $$NEW_TAG"; \
	if git rev-parse "$$NEW_TAG" >/dev/null 2>&1; then \
		echo "❌ Tag $$NEW_TAG already exists. Aborting."; \
		exit 1; \
	fi; \
	git tag "$$NEW_TAG"; \
	git push origin "$$NEW_TAG"

