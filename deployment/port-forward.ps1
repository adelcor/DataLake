# port-forward.ps1

# Start port-forwarding for Azurite service ports
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10000:10000 }  # Forward port 10000
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10001:10001 }  # Forward port 10001
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10002:10002 }  # Forward port 10002

# Start port-forwarding for PostgreSQL service port
Start-Job -ScriptBlock { kubectl port-forward service/postgres-service 5433:5432 }  # Forward port 5433 to 5432

# Start port-forwarding for Azure Function service port
Start-Job -ScriptBlock { kubectl port-forward service/azurefunctiontest-service 7071:7071 }  # Forward port 7071

# Start port-forwarding for CosmosDB Emulator service port
Start-Job -ScriptBlock { kubectl port-forward service/cosmosdb-emulator-service 8081:8081 }  # Forward port 8081

# Wait for user input to terminate the script
Write-Host "Port forwarding established. Press any key to continue..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
