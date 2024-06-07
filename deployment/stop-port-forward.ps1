# Get all port-forwarding jobs
$portForwardJobs = Get-Job | Where-Object { $_.Command -like "*kubectl port-forward*" }

# Stop and remove each port-forwarding job
foreach ($job in $portForwardJobs) {
    Stop-Job -Job $job  # Stop the job
    Remove-Job -Job $job  # Remove the job from the job list
}

# Display the remaining jobs (if any)
Get-Job
