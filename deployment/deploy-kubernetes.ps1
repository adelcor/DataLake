# Iniciar Minikube
minikube start

# Desplegar recursos de Kubernetes desde archivos YAML
kubectl apply -f azurefunction-deployment.yaml
kubectl apply -f azurefunction-service.yaml
kubectl apply -f azurite-deployment.yaml
kubectl apply -f azurite-service.yaml
kubectl apply -f postgres-deployment.yaml
kubectl apply -f postgres-service.yaml

# Mostrar los servicios para verificar que todo está funcionando
kubectl get services
