# jsrunner


<a href="https://travis-ci.org/OlegSokol/jsrunner"><img src="https://travis-ci.org/OlegSokol/jsrunner.svg?branch=master" alt="Build Status" /></a>
<a href="https://codecov.io/gh/OlegSokol/jsrunner"><img src="https://codecov.io/gh/OlegSokol/jsrunner/branch/master/graph/badge.svg"/></a>
<a href="https://www.codacy.com/app/OlegSokol/jsrunner?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=OlegSokol/jsrunner&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/cb4beafdb010431e96f7472dc1aaeb2b"/></a>




<p>
REST API cover for javax.script.ScriptEngine.
The application allows to run the java script on server side in separate threads.
Customer can add a java script(in body of the request), get console output/status('running', 'finish'), stop execution(for example if a script locked up in the cycle)

User can set the ScriptEngine by name(default 'nashorn') inside <b>resources/config.properties</b> and thread handler('threadHandler'(not 'blocking' api design), 'executorservice'(Implements 'blocking' api design, send request and waiting response(result of script executing)))
</p>

How to use:
1. Add script(method: 'POST', localhost:8080/execute, script: in request body)
2. Get list of executing or completed scripts(method: 'GET', localhost:8080/all)
3. Get script and information by specified id(method: 'GET', localhost:8080/script/{id})
4. Stop executing and remove script by specified id(method: 'DELETE', localhost:8080/remove/{id})

<img src="https://s8.hostingkartinok.com/uploads/images/2017/03/0436f8c1e53cbcec6c127c00dac254d5.jpg"/>
<img src="https://s8.hostingkartinok.com/uploads/images/2017/03/5e54bb557a21061e82aec1e3931ff6de.jpg" />

<h4> Technologies</h4>
 <ul>
   <li>Java 1.8</li>
   <li>Maven</li>
   <li>Spring Boot
     <ul>
        <li>core</li>
        <li>web</li>
        <li>hateoas</li>
     </ul>
   </li>
 </ul>
