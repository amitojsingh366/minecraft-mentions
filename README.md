# Minecarft Mentions

A simple [spigot](https://www.spigotmc.org/) plugin to allow mentioning players on your minecraft server

## How it works
### Finding if a message contains a mention
Messages are scanned using the regex `@(\S*)`
### Playing a sound on mentioned user's client
After a player logs into the server they are forced to download a resource pack with the mention sound in it, this same sound is played when that player gets mentioned

## How To Run
- Download the latest release from [here](https://github.com/amitojsingh366/minecraft-mentions/releases)
- Put it in your bukkit/spigot/paper server's plugin folder
- Run the server once to generate the config files
- Configure the plugin using the generated config file *(Located at `.plugins/MinecraftMentions/config.json`)*

## Credits
The mention sound effect was taken from the [DogeHouse](https://github.com/benawad/dogehouse/blob/staging/kibbeh/public/sound-effects/roomChatMention.wav) Repo and was originally made by [@MrDogeBro](https://github.com/MrDogeBro)

# License
minecarft-mentions is licensed under the MIT License

# Contributing
Find something that is lacking? Fork the project and pull request!