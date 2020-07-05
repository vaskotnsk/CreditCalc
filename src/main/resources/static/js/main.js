

$(document).ready(function() {
    $("#results_container").hide();

    $("#credit_form").submit(function (event) {
        //  Замена отправки формы на кастомную
        event.preventDefault();
        calc_ajax_submit();
    });
});

function calc_ajax_submit() {
    //  Заполнение параметров запроса и преобразование в JSON
    var param = JSON.stringify({
        summa: $("#id_summa").val(),
        srok: $("#id_srok").val(),
        procent: $("#id_procent").val()
    })

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/calc_credit",
        data: param,
        dataType: 'json',
        success : function(data) {
            //  Отображение блока результатов
            $("#results_container").show();
            //  Очистка таблицы графика платежей
            $("#table_platezh tbody").empty();
            //  Заполнение данных
            $("#platezh_sum").html(data.platezh_sum);
            $("#pereplata").html(data.pereplata);
            $("#summa_itogo").html(data.summa_itogo);
            //  Заполнение таблицы графика платежей
            data.platezh_list.forEach(
                function (item) {
                    $("#table_platezh tbody").append(
                        '<tr>'+
                        '<td style="text-align: center">'+item.data +'</td>'+
                        '<td style="text-align: center">'+item.nomer +'</td>'+
                        '<td style="text-align: center">'+item.summa +'</td>'+
                        '<td style="text-align: center">'+item.procent +'</td>'+
                        '<td style="text-align: center">'+item.dolg +'</td>'+
                        '<td style="text-align: center">'+item.ostatok +'</td>'+
                        '</tr>');
                }
            );
            $("#table_platezh tr:last").css("font-weight", "bold");
        },
        error: function (e) {
            $("#error_block").html("Ошибка запроса");
        }
    });
};

