import logging
import json
import os
import requests

logging.getLogger().addHandler(logging.StreamHandler())
logger = logging.getLogger()
logger.setLevel(logging.DEBUG)

API_GATEWAY_ENDPOINT = os.environ.get('API_GATEWAY_ENDPOINT') or 'https://156.35.82.22:8443/api'


def do_login(user):
    logger.debug(f"Login for user: {user['username']}")
    login_body = {'username': user['username'], 'password': user['password']}
    logger.debug(f"Doing login with body: {login_body}")
    r = requests.post(API_GATEWAY_ENDPOINT + "/auth/token", json=login_body, verify=False)
    logger.debug(f"Response code: {r.status_code}")
    logger.debug(f"Response: {r.text}")
    return json.loads(r.text)['tokenGenerated_']

######## Users #########
with open('img1.txt', 'r') as f:
    image1 = f.read()
with open('img2.txt', 'r') as f:
    image2 = f.read()
with open('img3.txt', 'r') as f:
    image3 = f.read()

users = [
    {
        "username":"alejgh",
        "password":"1234",
        "name":"Alejandro",
        "surname": "Gonzalez Hevia",
        "learning": ["en", "de", "ko"],
        "speaks": "es",
        "birthDate": "500049600",
        "base64Image": image1
    },
    {
        "username":"carmenpl",
        "password":"4321",
        "name":"Carmen",
        "surname": "Perez Lopez",
        "learning": ["en", "fr", "it"],
        "speaks": "es",
        "birthDate": "500015600",
        "base64Image": ""
    },
    {
        "username":"thewillyhuman",
        "password":"56789",
        "name":"Guillermo",
        "surname": "Facundo Colunga",
        "learning": ["en", "de", "it"],
        "speaks": "es",
        "birthDate": "500009600",
        "base64Image": image2
    },
    {
        "username": "mrmboy",
        "password": "1234",
        "name":"Pablo",
        "surname": "Menéndez Suárez",
        "learning": ["en", "de", "ko"],
        "speaks": "es",
        "birthDate": "500049600",
        "base64Image": image3
    }
]

for user in users:
    logger.debug(f"Adding user: {user['username']}")
    r = requests.post(API_GATEWAY_ENDPOINT + "/users", json=user, verify=False)
    logger.debug(f"Response code: {r.status_code}")
    logger.debug(f"Response: {r.text}")
    if r.status_code == 201:
        user['id'] = json.loads(r.text)['id']


####### Posts ########
posts = [
    {
        'content': """26일 그랜드오픈하는 여의도 더현대서울 백화점 내부설계가 최강이네.\n공중정원같은 식당층과 대형 분수, 층고 높고 조경 좋고..기분좋은 공간을 만들었다.\n금돼지 몽탄 에그슬럿 뜨락 신상맛집 다 입점했고 인스타스타 OVN과 블루보틀 백화점 첫 입성.\n현백 자체 스타 브랜드 와인웍스 3호점도!
        """
    },
    {
        'content': """Machine learning models with similar performance on the same task are called a Rashomon set.

        How do we deal with the fact that models in the Rashomon set might have different decision making mechanisms?
        Which one is the correct model?
        """
    },
    {
        'content': "English Vocabulary: MAGIC\nhex\nspell\ncharm\ncurse\ndevilry\nvoodoo\nsorcery\nwitching\nwitchery\nwizardry\nincantation\nspellworking\nenchantment"
    },
    {
        'content': "Desde que he ido a vivir a España escucho mucho la palabra siesta.\nCreo que es..."
    },
    {
        'content': ""
    }
]

post_authors = [0, 2, 3, 1]

for user_idx, post in zip(post_authors, posts):
    u = users[user_idx]
    token = do_login(u)
    headers = {'token': token}
    logger.debug(f"token: {token}")
    logger.debug(f"Adding new post: {post}")
    r_post = requests.post(API_GATEWAY_ENDPOINT + "/posts", json=post, headers=headers, verify=False)
    logger.debug(f"Response code: {r_post.status_code}")
    logger.debug(f"Response: {r_post.text}")

######## Chats ##########
user_chats = [(0, 1), (2, 3), (3, 1)]
chats = []
for user1_idx, user2_idx in user_chats:
    user1 = users[user1_idx]
    user2 = users[user2_idx]
    token = do_login(user1)
    logger.debug(f"Creating new chat between users {user1['id']} and {user2['id']}")
    headers = {'token': token}
    r_chat = requests.post(API_GATEWAY_ENDPOINT + "/chats/", json={'user2': user2['id']}, headers=headers, verify=False)
    logger.debug(f"Response code: {r_chat.status_code}")
    logger.debug(f"Response: {r_chat.text}")
    chats.append(json.loads(r.text)['id'])

####### messages ###########

messages = [
    {
        "text": "Hola, que tal estás?"
    },
    {
        "text": "Hello, I'm doing fine. How about you?"
    },
    {
        "text": "안녕하새요 ~~"
    },
    {
        "text": "Hola, podemos practicar un poco el inglés?"
    },
]

# chat that each message belongs to
messages_chat = [1, 1, 0, 2]

# author of each message
messages_author = [2, 3, 0, 1]

for msg, chat_idx, author_idx in zip(messages, messages_chat, messages_author):
    chat = chats[chat_idx]
    author = users[author_idx]
    token = do_login(users[author_idx])
    logger.debug(f"Creating new message between users {user1['id']} and {user2['id']}")
    headers = {'token': token}
    r_chat = requests.post(API_GATEWAY_ENDPOINT + "/chats/" + str(chat['id']) + "/messages",
        json=msg, headers=headers, verify=False)
    logger.debug(f"Response code: {r_chat.status_code}")