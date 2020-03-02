if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'PeregrineJS'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'PeregrineJS'.");
}
var PeregrineJS = function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var equals = Kotlin.equals;
  var throwCCE = Kotlin.throwCCE;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var substring = Kotlin.kotlin.text.substring_fc3b62$;
  var toShort = Kotlin.toShort;
  var toChar = Kotlin.toChar;
  var equals_0 = Kotlin.kotlin.text.equals_igcy3c$;
  var Unit = Kotlin.kotlin.Unit;
  function UrlColumnMap(url, groupName) {
    this.url = url;
    this.groupName = groupName;
  }
  UrlColumnMap.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UrlColumnMap',
    interfaces: []
  };
  function main(args) {
    var tmp$;
    println('hello - im here to sign you into a tiss course');
    if (equals(document.URL, 'https://tiss.tuwien.ac.at/education/course/courseRegistration.xhtml') || equals(document.URL, 'https://tiss.tuwien.ac.at/education/course/groupList.xhtml')) {
      println('clicking');
      var buttons = document.getElementsByTagName('input');
      println(buttons.length);
      var button = Kotlin.isType(tmp$ = buttons[0], HTMLInputElement) ? tmp$ : throwCCE();
      button.click();
    }
    var url = document.URL;
    var courseNrRegex = Regex_init('courseNr');
    var match = courseNrRegex.find_905azu$(url);
    var numberRange = new IntRange(ensureNotNull(match).range.endInclusive + 2 | 0, match.range.endInclusive + 7 | 0);
    var courseNr = substring(url, numberRange);
    println(courseNr);
    var path = 'chrome-extension://mmojljechnociddmllimabfekkidbpgh/PeregrineJVM/urlColumnMatches/match' + courseNr + '.JSON';
    clickAtPos(path);
  }
  function clickAtPos$lambda(closure$xhr, closure$fileInput) {
    return function (it) {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14;
      if (closure$xhr.readyState === toShort(4) && closure$xhr.status === toShort(200)) {
        closure$fileInput.v = closure$xhr.responseText;
        var urlColumnMap = JSON.parse(closure$fileInput.v);
        println('searching for: ' + urlColumnMap.groupName);
        var groupWrappers = document.getElementsByClassName('groupWrapper');
        tmp$ = groupWrappers.length;
        for (var i = 0; i < tmp$; i++) {
          var currentGroupName = (tmp$_4 = (tmp$_3 = (tmp$_2 = (tmp$_1 = (tmp$_0 = groupWrappers[i]) != null ? tmp$_0.children : null) != null ? tmp$_1[0] : null) != null ? tmp$_2.children : null) != null ? tmp$_3[0] : null) != null ? tmp$_4.textContent : null;
          var currentGroupNameTrimmed = false;
          while (!currentGroupNameTrimmed) {
            if (ensureNotNull(currentGroupName != null ? currentGroupName.charCodeAt(currentGroupName.length - 1 | 0) : null) < toChar(48) || ensureNotNull(currentGroupName != null ? currentGroupName.charCodeAt(currentGroupName.length - 1 | 0) : null) > toChar(91)) {
              var $receiver = currentGroupName;
              var endIndex = currentGroupName.length - 1 | 0;
              currentGroupName = $receiver.substring(0, endIndex);
            }
             else {
              currentGroupNameTrimmed = true;
            }
          }
          if (currentGroupName != null) {
            println(currentGroupName.length);
          }
          println(currentGroupName);
          if (equals_0(currentGroupName, urlColumnMap.groupName)) {
            var lines = (tmp$_10 = (tmp$_9 = (tmp$_8 = (tmp$_7 = (tmp$_6 = (tmp$_5 = groupWrappers[i]) != null ? tmp$_5.children : null) != null ? tmp$_6[1] : null) != null ? tmp$_7.children : null) != null ? tmp$_8[0] : null) != null ? tmp$_9.children : null) != null ? tmp$_10[0] : null;
            println(lines != null ? lines.tagName : null);
            var button = Kotlin.isType(tmp$_14 = (tmp$_13 = (tmp$_12 = (tmp$_11 = lines != null ? lines.children : null) != null ? tmp$_11[lines.children.length - 1 | 0] : null) != null ? tmp$_12.children : null) != null ? tmp$_13[0] : null, HTMLInputElement) ? tmp$_14 : throwCCE();
            println(button.id);
            button.click();
          }
        }
      }
      return Unit;
    };
  }
  function clickAtPos(path) {
    var fileInput = {v: null};
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = clickAtPos$lambda(xhr, fileInput);
    xhr.open('GET', path);
    xhr.send();
  }
  _.UrlColumnMap = UrlColumnMap;
  _.main_kand9s$ = main;
  _.clickAtPos_61zpoe$ = clickAtPos;
  main([]);
  Kotlin.defineModule('PeregrineJS', _);
  return _;
}(typeof PeregrineJS === 'undefined' ? {} : PeregrineJS, kotlin);
