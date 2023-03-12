import mysql.connector
import os
from dotenv import load_dotenv

load_dotenv()

DB_HOST = os.getenv('DB_HOST')
DB_USER = os.getenv('DB_USER')
DB_PASSWORD = os.getenv('DB_PASSWORD')
DB_DATABASE = os.getenv('DB_DATABASE')

mydb = mysql.connector.connect(
    host=DB_HOST,
    user=DB_USER,
    password=DB_PASSWORD,
    database=DB_DATABASE
)
mycursor = mydb.cursor()

with open('Rusterdatabase', 'r') as f:
    script = f.read()

statements = script.split(';')

for statement in statements:
    if statement.strip() != '':
        mycursor.execute(statement)
        table_name = statement.split()[2].replace('`', '')
        print(f"The table [{table_name}] has been created.")

mydb.commit()
mycursor.close()
mydb.close()

print("All Table Created successfully!")
print("Script Create By Azran Hossain")
