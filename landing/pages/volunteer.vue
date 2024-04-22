<script setup>
const tokenCookie = useCookie('token');

const em_columns = [{
    key: 'emergencyname',
    label: 'Emergencia'
}, {
    key:'institutionname',
    label: 'Institución'
}, {
    key: 'coordinatorname',
    label: 'Coordinador'
}]

const emergencies = await $fetch('http://localhost:8080/api/emergency/actives/view', {
    method: 'GET',
    params: {
        token: tokenCookie.value
    }
});

const em_page = ref(1)
const em_pageCount = 10

const em_rows = computed(() => {
  return emergencies.slice((em_page.value - 1) * em_pageCount, (em_page.value) * em_pageCount)
})

const items = (row) => [
  [{
    label: 'Edit',
    icon: 'i-heroicons-pencil-square-20-solid',
    click: () => console.log('Edit', row.id)
  }, {
    label: 'Duplicate',
    icon: 'i-heroicons-document-duplicate-20-solid'
  }], [{
    label: 'Archive',
    icon: 'i-heroicons-archive-box-20-solid'
  }, {
    label: 'Move',
    icon: 'i-heroicons-arrow-right-circle-20-solid'
  }], [{
    label: 'Delete',
    icon: 'i-heroicons-trash-20-solid'
  }]
]

</script>

<template>
    <div class="flex justify-center items-center content-below-appbar">
        <UCard>
            <h1 class="text-primary text-center text-inherit text-2xl pb-5 pt-2">Emergencias</h1>
            <UTable :rows="em_rows" :columns="em_columns">
                <template #actions-data="{ row }">
                    <UDropdown :items="items(row)">
                        <UButton color="gray" variant="ghost" icon="i-heroicons-ellipsis-horizontal-20-solid" />
                    </UDropdown>
                </template>
            </UTable>
            <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
            <UPagination v-model="em_page" :page-count="em_pageCount" :total="emergencies.length" />
            </div>
        </UCard>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>