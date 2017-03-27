# jsrunner

<div>
  <img src="https://travis-ci.org/OlegSokol/jsrunner.svg?branch=master" alt="Build Status" />
</div>


<p>
REST API cover for javax.script.ScriptEngine.
The application allows to run java script on server side in separate threads.
Customer can add script, get console output/status('running', 'finish'), stop execution(for example if script hang out in cycle)

User can set ScriptEngine name(for examle 'nashorn') in <b>resources/config.properties</b> and thread handler('thread', 'executorservice'(in future))
</p>

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
