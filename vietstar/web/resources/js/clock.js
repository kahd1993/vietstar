/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function GetClock() {
    var d = new Date();
    var nmonth = d.getMonth(), ndate = d.getDate(), nyear = d.getYear();
    if (nyear < 1000)
        nyear += 1900;
    var nhour = d.getHours(), nmin = d.getMinutes(), nsec = d.getSeconds(), ap;

    if (nhour === 0) {
        ap = " AM";
        nhour = 12;
    }
    else if (nhour < 12) {
        ap = " AM";
    }
    else if (nhour === 12) {
        ap = " PM";
    }
    else if (nhour > 12) {
        ap = " PM";
        nhour -= 12;
    }

    if (nmin <= 9)
        nmin = "0" + nmin;
    if (nsec <= 9)
        nsec = "0" + nsec;

    document.getElementById('clockbox').innerHTML = "" + ndate + "-" + (nmonth + 1) + "-" + nyear + " " + nhour + ":" + nmin + ":" + nsec + ap + "";
}

window.onload = function () {
    GetClock();
    setInterval(GetClock, 1000);
};