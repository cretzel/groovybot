import java.util.Stack

scenario "1 is pushed onto empty stack", {
  given "an empty stack",{
    stack = new Stack()
  }

  when "1 is pushed", {
      stack.push(1)
  }

  then "then the stack should not be empty", {
      stack.empty.shouldBe false
  }
}

scenario "pop is called on empty stack", {
  given "an empty stack",{
    stack = new Stack()
  }

  when "pop is called", {
    popnull = {
      stack.pop()
    }
  }

  then "an exception should be thrown", {
    ensureThrows(RuntimeException){
      popnull()
    }
  }
  
  and "then the stack should still be empty", {
      stack.empty.shouldBe true
    }

}