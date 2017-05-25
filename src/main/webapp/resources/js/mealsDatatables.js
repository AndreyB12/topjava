/**
 * Created by butkoav on 20.05.2017.
 */
var ajaxUrl = 'ajax/profile/meals/';
var ajaxDataUrl = ajaxUrl;
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
                "desc"
            ]
        ]
    });
    makeEditable();
    makeFilterForm();
});

function makeFilterForm() {
    $('#filterForm').submit(function () {
        var form = $('#filterForm');
        ajaxDataUrl = ajaxUrl + "filter?" + form.serialize();
        updateTable(ajaxDataUrl);
        return false;
    });
}

function filter() {

}

function clearFilter() {
    $('#filterForm')[0].reset();
    ajaxDataUrl = ajaxUrl;
    updateTable(ajaxDataUrl);
}