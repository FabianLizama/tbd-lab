<script default>

</script>

<template>
    <div class="flex justify-center items-center content-below-appbar">
        <UCard class="flex justify-center items-center">
            <div class="flex justify-center items-center">
                <UButton class="w-40 h-40 m-10 text-center" size="xl" label="Crear Emergencia" color="primary" to="/createEmergency" />
                <UButton to="/coordTables" class="w-40 h-40 m-10 text-center" size="xl" label="Ver Cantidad Voluntarios" color="primary" />
            </div>
        </UCard>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>