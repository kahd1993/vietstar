/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function ()
{
    var lis = document.getElementById('cssdropdown').getElementsByTagName('li');
    for (i = 0; i < lis.length; i++)
    {
        var li = lis[i];
        if (li.className === 'headlink')
        {
            li.onmouseover = function () {
                this.getElementsByTagName('ul').item(0).style.display = 'block';
            };
            li.onmouseout = function () {
                this.getElementsByTagName('ul').item(0).style.display = 'none';
            };
        }
    }
};