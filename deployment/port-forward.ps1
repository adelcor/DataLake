# port-forward.ps1

Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10000:10000 }
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10001:10001 }
Start-Job -ScriptBlock { kubectl port-forward service/azurite-service 10002:10002 }

# Espera a que el usuario presione una tecla para terminar
Write-Host "Port forwarding establecido para los puertos 10000, 10001 y 10002. Presiona cualquier tecla para terminar..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
