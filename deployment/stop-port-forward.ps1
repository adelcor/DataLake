# Obtener todos los trabajos en segundo plano relacionados con el reenvío de puertos
$portForwardJobs = Get-Job | Where-Object { $_.Command -like "*kubectl port-forward*" }

# Detener y eliminar cada trabajo
foreach ($job in $portForwardJobs) {
    # Detener el trabajo
    Stop-Job -Job $job
    # Eliminar el trabajo
    Remove-Job -Job $job
}

# Mostrar el estado de todos los trabajos para verificar que todos han sido eliminados
Get-Job
