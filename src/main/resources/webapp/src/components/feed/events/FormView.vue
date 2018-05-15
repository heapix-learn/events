<template>
    <div>
        <div class="generated-form row">
            <div v-for="(value, key) in info" class="card-content black-text">
                <div class="row">
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
        }
    }
</script>

<style>

</style>
