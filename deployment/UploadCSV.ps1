# Definir variables generales
$containerName = "micontenedor"
$connectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;"

# Función para verificar y crear contenedor si no existe
function Ensure-Container {
    $exists = az storage container exists `
        --name $containerName `
        --connection-string $connectionString `
        --output tsv --query exists
    if (-Not [System.Convert]::ToBoolean($exists)) {
        Write-Host "Creando contenedor $containerName."
        az storage container create `
            --name $containerName `
            --connection-string $connectionString
        Write-Host "Contenedor $containerName creado."
    } else {
        Write-Host "El contenedor $containerName ya existe."
    }
}

# Función para subir archivos a Azure Blob Storage
function Upload-Blob {
    param (
        [Parameter(Mandatory=$true)]
        [string]$fileName,
        [Parameter(Mandatory=$true)]
        [string]$blobName
    )

    if (-Not (Test-Path $fileName)) {
        Write-Host "El archivo $fileName no existe."
        return
    }

    $blobExists = az storage blob exists `
        --container-name $containerName `
        --name $blobName `
        --connection-string $connectionString `
        --output tsv --query exists

    if ([System.Convert]::ToBoolean($blobExists)) {
        Write-Host "El blob $blobName ya existe en el contenedor $containerName."
    } else {
        try {
            Write-Host "Iniciando la subida del archivo $fileName al blob $blobName."
            az storage blob upload `
                --container-name $containerName `
                --file $fileName `
                --name $blobName `
                --connection-string $connectionString
            Write-Host "Archivo $fileName subido exitosamente como $blobName."
        } catch {
            Write-Host "Error al subir el archivo: $_"
        }
    }
}

# Crear el contenedor si no existe
Ensure-Container

# Llamar a la función para subir cada archivo
Upload-Blob -fileName "tortilla_prices.csv" -blobName "tortilla_prices.csv"
Upload-Blob -fileName "SalmonandSeaTroutNets1952-2022.csv" -blobName "SalmonandSeaTroutNets1952-2022.csv"
Upload-Blob -fileName "meteorite-landings.csv" -blobName "meteorite-landings.csv"

# Listar los blobs en el contenedor para verificar
try {
    Write-Host "Listando los blobs en el contenedor $containerName."
    az storage blob list `
        --container-name $containerName `
        --output table `
        --connection-string $connectionString
} catch {
    Write-Host "Error al listar blobs: $_"
}
