# Define the container name and connection string
$containerName = "micontenedor"
$connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;"

# Function to ensure the container exists
function Ensure-Container {
    $exists = az storage container exists `
        --name $containerName `
        --connection-string $connectionString `
        --output tsv --query exists
    if (-Not [System.Convert]::ToBoolean($exists)) {
        Write-Host "Creating container $containerName."
        az storage container create `
            --name $containerName `
            --connection-string $connectionString
        Write-Host "Container $containerName created."
    } else {
        Write-Host "Container $containerName already exists."
    }
}

# Function to upload a blob to the container
function Upload-Blob {
    param (
        [Parameter(Mandatory=$true)]
        [string]$fileName,
        [Parameter(Mandatory=$true)]
        [string]$blobName
    )

    if (-Not (Test-Path $fileName)) {
        Write-Host "File $fileName does not exist."
        return
    }

    $blobExists = az storage blob exists `
        --container-name $containerName `
        --name $blobName `
        --connection-string $connectionString `
        --output tsv --query exists

    if ([System.Convert]::ToBoolean($blobExists)) {
        Write-Host "Blob $blobName already exists in container $containerName."
    } else {
        try {
            Write-Host "Starting upload of file $fileName to blob $blobName."
            az storage blob upload `
                --container-name $containerName `
                --file $fileName `
                --name $blobName `
                --connection-string $connectionString
            Write-Host "File $fileName successfully uploaded as $blobName."
        } catch {
            Write-Host "Error uploading file: $_"
        }
    }
}

# Ensure the container exists
Ensure-Container

# Upload the specified files as blobs
Upload-Blob -fileName "tortilla_prices.csv" -blobName "tortilla_prices.csv"
Upload-Blob -fileName "SalmonandSeaTroutNets1952-2022.csv" -blobName "SalmonandSeaTroutNets1952-2022.csv"
Upload-Blob -fileName "meteorite-landings.csv" -blobName "meteorite-landings.csv"

# List the blobs in the container
try {
    Write-Host "Listing blobs in container $containerName."
    az storage blob list `
        --container-name $containerName `
        --output table `
        --connection-string $connectionString
} catch {
    Write-Host "Error listing blobs: $_"
}
