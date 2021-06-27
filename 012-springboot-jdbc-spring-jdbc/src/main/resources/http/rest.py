import requests
import random
import json

url = "http://localhost:22222/model/"
headers = {
    "Content-Type": "application/json",
    "Accept": "application/json"
}


def post():
    data = {
        "key": randomstr(4),
        "value": randomstr(8),
        "remark": randomstr(8)
    }
    res = requests.post(
        url=url,
        headers=headers,
        data=json.dumps(data, ensure_ascii=False) # data=json.dumps(data) <==> json=data
    )
    print(res.json())


def truncate():
    res = requests.delete(url=url, headers=headers)
    print(res.text)


def get(id):
    res = requests.get(url=url + str(id), headers=headers)
    if (res.text == ""):  # TODO restful response没有规范化
        print(res.text)
    else:
        print(res.json())


def update(id):
    data = {
        "id": id,
        "key": "__" + randomstr(6).upper() + "__",
        "value": "__" + randomstr(6).upper() + "__",
        "remark": "__" + randomstr(6).upper() + "__"
    }
    res = requests.put(url=url + str(id), headers=headers, json=data)
    print(res.text)


def delete(id):
    res = requests.delete(url=url + str(id), headers=headers)
    print(res.text)


def randomstr(num):
    x = "qwertyuiopasdfghjklzxcvbnm"
    x += x.upper()
    x += "0123456789"
    return "".join(i for i in random.choices(x, k=int(num)))


def test():
    randomstr(10)


if __name__ == '__main__':

    ### 删[ALL]
    input("\n----------------------------删[ALL] (ENTER)")
    truncate()

    ### 增
    input("\n----------------------------增  (ENTER)")
    for i in range(20):
        pass
        post()

    ### 查[ALL]
    input("\n----------------------------查[ALL]  (ENTER)")
    get("")

    ### 改
    input("\n----------------------------改  (ENTER)")
    for i in range(1, 11):
        update(i)

    ### 查
    input("\n----------------------------查  (ENTER)")
    for i in range(1, 11):  # [x, y)
        get(i)

    ### 删
    input("\n----------------------------删  (ENTER)")
    for i in range(1, 11):
        delete(i)

    ### 查[ALL]
    input("\n----------------------------查[ALL]  (ENTER)")
    get("")

    ### 删[ALL]
    input("\n----------------------------删[ALL]  (ENTER)")
    truncate()

    ### 查[ALL]
    input("\n----------------------------查[ALL]  (ENTER)")
    get("")
