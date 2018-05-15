<template>
  <div>
    <div class="generated-form row">
      <div v-for="(field, index) in formInputs" :key="index" class="col s12">
        <template v-if="['text', 'number'].indexOf(field.type) > -1">
          <div class="input-field col s6 offset-s3">
            <input :disabled="isPreview" :id="`form-field-${index}`" type="text" v-model="userFormInput[field.label]">
            <label :for="`form-field-${index}`">{{field.label}}</label>
          </div>
        </template>
        <template v-else-if="field.type === 'select'">
          <div class="input-field col s6 offset-s3">
            <select :disabled="isPreview" id="main-select" v-model="userFormInput[field.label]">
              <option v-for="(formOption, index) in field.options.split(', ')" :key="index" :value="formOption">{{formOption}}</option>
            </select>
            <label>{{field.label}}</label>
          </div>
        </template>
        <template v-else-if="field.type === 'image'">
            <form class="col s6 offset-s3">
              <div class="file-field input-field">
                <div class="btn green">
                  <span>File</span>
                  <input :disabled="isPreview" type="file">
                </div>
                <div class="file-path-wrapper">
                  <input :disabled="isPreview" :placeholder="field.label" class="file-path" type="text">
                </div>
              </div>
            </form>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormGeneratorView',
  props: ['formInputs', 'isPreview', 'userFormInput'],
  mounted () {
    M.FormSelect.init(document.querySelectorAll('select'))
  }
}
</script>

<style>

</style>
