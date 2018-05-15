<template>
    <div>
        <div class="generated-form row">
            <div v-for="(value, key) in info" class="card-content black-text">
                <div v-if="value.type === 'img'" class="row">
                    <img  class="small" :src="value.image" alt="logo">
                </div>
                <div v-else class="row">
                    <p class="col s6">{{key}} : <span>{{value}}</span></p>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
    export default {
        name: 'FormView',
        props: ['info'],
        mounted() {
        },
    }
</script>

<style>
    .small {
        max-width: 200px;
        max-height: 200px;
    }
</style>
