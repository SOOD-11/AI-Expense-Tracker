from app.schema.Expense import Expense
from app.utils.MessageUtil import MessageUtil
from app.service.LLMService import LLMService
class MessageService:
    def __init__(self):

       self.messageUtil=MessageUtil()
       self.llmService=LLMService()
       
    def    process_message(self,message):
        if self.messageUtil.isBankSms(message):
              

              return self.llmService.runLLM(message=message)
        else :

            return Expense(
                amount=None,
                merchant=None,
                currency=None
            )