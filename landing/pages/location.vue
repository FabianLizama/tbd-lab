<script setup>
import { ref, onMounted } from 'vue'
import { Loader } from '@googlemaps/js-api-loader'

const initialCenter = { lat: -33.4489, lng: -70.6693 } // Coordenadas de Santiago, Chile
const center = ref({ ...initialCenter })
const selectedLocation = ref({ ...initialCenter })
const inputLocalization = ref('Santiago de Chile, Región Metropolitana, Chile')
const config = useRuntimeConfig()
let map
let marker

const initAutocomplete = () => {
    const input = document.getElementById('input-location')
    const autocomplete = new google.maps.places.Autocomplete(input, {
        componentRestrictions: { country: 'cl' } // Restringir sugerencias a Chile
    })
    autocomplete.addListener('place_changed', () => {
        const place = autocomplete.getPlace()
        if (place.geometry) {
            selectedLocation.value = {
                lat: place.geometry.location.lat(),
                lng: place.geometry.location.lng(),
            }
            center.value = selectedLocation.value
            inputLocalization.value = place.formatted_address
            map.setCenter(selectedLocation.value)
            marker.setPosition(selectedLocation.value)
        }
    })
}

const resetMap = () => {
    center.value = { ...initialCenter }
    selectedLocation.value = { ...initialCenter }
    inputLocalization.value = 'Santiago de Chile, Región Metropolitana, Chile'
    map.setCenter(center.value)
    marker.setPosition(center.value)
}

onMounted(() => {
    const loader = new Loader({
        apiKey: 'AIzaSyDxMgO4Z6RYYugJnQvrc6Iyjt9Ea6coMbo',
        version: 'weekly',
        libraries: ['places'],
    })

    loader.load().then(() => {
        map = new google.maps.Map(document.getElementById('map'), {
            center: center.value,
            zoom: 15,
        })

        marker = new google.maps.Marker({
            position: center.value,
            map: map,
        })

        map.addListener('click', (event) => {
            selectedLocation.value = {
                lat: event.latLng.lat(),
                lng: event.latLng.lng(),
            }
            inputLocalization.value = `Lat: ${selectedLocation.value.lat}, Lng: ${selectedLocation.value.lng}`
            marker.setPosition(selectedLocation.value)
        })

        initAutocomplete()
    })
})
</script>

<template>
    <div class="flex justify-center items-center content-below-appbar px-28">
        <UCard class="w-4/5">
            <UInput 
                size="xl" 
                placeholder="Dirección" 
                v-model="inputLocalization" 
                icon="i-heroicons-map-pin" 
                id="input-location"
            />
            <UContainer class="flex py-8 justify-center">
                <div id="map" class="w-full" style="height: 500px"></div>
            </UContainer>
            <div class="flex justify-center py-4">
                <UButton @click="resetMap">Resetear Mapa</UButton>
            </div>
        </UCard>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px);
}
</style>
