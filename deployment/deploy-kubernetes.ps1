# Start Minikube
minikube start

# Apply the deployment and service configurations for Azure Function
kubectl apply -f azurefunction-deployment.yaml  # Apply the Azure Function deployment
kubectl apply -f azurefunction-service.yaml  # Apply the Azure Function service

# Apply the deployment and service configurations for Azurite
kubectl apply -f azurite-deployment.yaml  # Apply the Azurite deployment
kubectl apply -f azurite-service.yaml  # Apply the Azurite service

# Apply the deployment and service configurations for PostgreSQL
kubectl apply -f postgres-deployment.yaml  # Apply the PostgreSQL deployment
kubectl apply -f postgres-service.yaml  # Apply the PostgreSQL service

# Retrieve and display the list of services
kubectl get services  # Get the list of all services in the cluster
