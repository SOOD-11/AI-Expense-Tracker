from dotenv import load_dotenv
from typing import Optional
from langchain_core.prompts import ChatPromptTemplate,MessagesPlaceholder
from pydantic import BaseModel,Field
from langchain_openai import ChatOpenAI
from langchain_mistralai import ChatMistralAI
from app.schema.Expense import Expense
import os
class LLMService:
    def __init__(self):
        load_dotenv()
        self.prompt=ChatPromptTemplate.from_messages(
            [


                (
                    "system",
                    "You are an expert extraction algorithm ."
                    "only extract relevent  information from the  text."
                    "if u didnt know the value of an  attribute asked to  extract,"
                    "return null for attribute's value "

                ),

                ('human',"{text}"),

            ]
        )

        self.apiKey=os.getenv("OPEN_AI_API_KEY")
        self.llm=ChatMistralAI(api_key=self.apiKey,model="mistral-large-latest")
        self.runnable=self.prompt |self.llm.with_structured_output(schema= Expense)

    def runLLM(self,message):
        return self.runnable.invoke({"text": message}) 
        

        







    