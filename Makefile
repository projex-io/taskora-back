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
	@LAST_TAG=$$(git describe --tags --abbrev=0 2>/dev/null || echo "v0.0.0"); \
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
	# Uncomment below when ready \
	git tag "$$NEW_TAG"; \
	git push origin "$$NEW_TAG"
