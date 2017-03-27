# jsrunner

<div>
<a href="https://travis-ci.org/OlegSokol/jsrunner"><img src="https://travis-ci.org/OlegSokol/jsrunner.svg?branch=master" alt="Build Status" /></a>
</div>


<p>
REST API cover for javax.script.ScriptEngine.
The application allows to run an java script on server side in separate threads.
Customer can add an java script(in the body of the request), get console output/status('running', 'finish'), stop execution(for example if an script hang out in the cycle)

User can set the ScriptEngine by name(default 'nashorn') inside <b>resources/config.properties</b> and thread handler('thread', 'executorservice'(in the future))
</p>

How to use:
1. Add script(method: 'POST', localhost:8080/execute, script: in request body)
2. Get list of executing or completed scripts(method: 'GET', localhost:8080/all)
3. Get script and information by specified id(method: 'GET', localhost:8080/script/{id})
4. Stop executing and remove script by specified id(method: 'DELETE', localhost:8080/remove/{id})

<img src="https://lh6.googleusercontent.com/JTt3MaD93KThd5NNKMe-bYvLej1uf1DFICe1PtNl1IeAo1pIwTZz4WsaFjh-555RGs7BvbbM2yV3PgA=w1366-h672"/>
<img src="https://lh3.googleusercontent.com/exDcVkDuDQ1wYollYtCXDCioK6YA1quco82W-QcRuAfjvn5iZ5_0oLlsT00P4_IDLX8LGvyp3FQyV58=w1366-h672" />

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
