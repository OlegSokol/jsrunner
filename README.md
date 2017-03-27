# jsrunner

<div>
  <img src="https://travis-ci.org/OlegSokol/jsrunner.svg?branch=master" alt="Build Status" />
</div>


<p>
REST API cover for javax.script.ScriptEngine.
The application allows to run an java script on server side in separate threads.
Customer can add an java script(in the body of the request), get console output/status('running', 'finish'), stop execution(for example if an script hang out in the cycle)

User can set the ScriptEngine name(for examle 'nashorn') inside <b>resources/config.properties</b> and thread handler('thread', 'executorservice'(in future))
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
