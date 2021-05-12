#!/usr/bin/env bash

cd $(dirname "$0")

source config.sh
set -x
if ! helm repo list|grep $REPO_URL > /dev/null; then
    helm repo add inventi $REPO_URL
fi

helm upgrade -i $RELEASE inventi/$CHART --version $CHART_VER \
    -f helm-values.yaml \
    --set image.tag=$VERSION \
    ${@}
