import com.groovybot.controller.handler.impl.ScriptBlipHandlerImpl

scenario "script blip handler is called with non-script-blip prefix", {
  given "a script blip handler",{
    handler = new ScriptBlipHandlerImpl()
  }

  when "handler is called to with a non-script-blip prefix"
  then "then the handler should throw an IllegalArgumentException"
}

