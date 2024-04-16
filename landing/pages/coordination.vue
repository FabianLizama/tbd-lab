<script setup>
const columns = [{
    key: 'emergency_id',
    label: 'ID',
    sortable: true
}, {
    key: 'institution_id',
    label: 'Institución'
}, {
    key: 'coordintator_id',
    label: 'Coordinador'
}, {
    key: 'name',
    label: 'Nombre',
    sortable: true
}, {
    key: 'emergency_state',
    label: 'Estado'
}
]

const emergencies = ref([]);

const query = ref('')

const filteredRows = computed(() => {
  if (!query.value) {
    return emergencies
  }

return emergencies.value.filter((emergency) => {
    return Object.values(emergency).some((value) => {
      return String(value).toLowerCase().includes(query.value.toLowerCase())
    })
  })
})

</script>

<template>
    <UCard class="w-3/5">
        <UTable :rows="emergencies" />
    </UCard>
</template>