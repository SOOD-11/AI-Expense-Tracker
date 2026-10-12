from pydantic import BaseModel , Field
from typing import Optional
class Expense(BaseModel):
    amount :Optional[str]= Field(defult=None,title="expense",description= "Expense made in the transaction")
    merchant: Optional[str] = Field(default=None,title="merchant",description="Merchant name to whom transaction been made")
    currency:Optional[str]=Field(default=None,title="currency",description="currency of the transaction")
