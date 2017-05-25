var ajaxUrl = 'ajax/admin/users/';
var ajaxDataUrl = ajaxUrl;
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function userEnabled(id, enabled) {
    $.ajax({
        type: "POST",
        cache: false,
        url: ajaxUrl + id + "/enabled/" + enabled,
        success: function () {
            successNoty('Saved');
        }
    });
    updateTable(ajaxDataUrl);
}