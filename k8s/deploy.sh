#!/usr/bin/env bash

cd $(dirname "$0")

source config.sh
set -x
if ! helm repo list|grep $REPO_URL > /dev/null; then
    helm repo add inventi $REPO_URL
fi

env_override_file=helm-values.env.${ENVIRONMENT}.yaml
env_override_option="$([ -f $env_override_file ] && echo "-f $env_override_file")"

helm upgrade -i $RELEASE inventi/$CHART --version $CHART_VER \
    -f helm-values.yaml \
    --set image.tag=$VERSION \
    ${@}
