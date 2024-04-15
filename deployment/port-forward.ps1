# port-forward.ps1

Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10000:10000 }
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10001:10001 }
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10002:10002 }

Start-Job -ScriptBlock { kubectl port-forward service/postgres-service 5433:5432 }

Start-Job -ScriptBlock { kubectl port-forward service/azurefunctiontest-service 7071:7071 }

Start-Job -ScriptBlock { kubectl port-forward service/cosmosdb-emulator-service 8081:8081 }


# Espera a que el usuario presione una tecla para terminar
Write-Host "Port forwarding establecido. Presiona cualquier tecla para continuar..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
