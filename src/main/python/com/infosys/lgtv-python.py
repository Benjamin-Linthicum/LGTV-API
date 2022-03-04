from pylgtv import WebOsClient

import sys
import logging

logging.basicConfig(stream=sys.stdout, level=logging.INFO)

try:
    webos_client = WebOsClient('192.168.0.112')

except:
    print("Error connecting to the TV")

match sys.argv[1]:
    case "launch-app":
        try:
            webos_client.launch_app(sys.argv[2])
            print("Success")
        except:
            print("Failed to launch app.")

    case "set-mute":
        try:
            webos_client.set_mute('mute')
            print("Success")
        except:
            print("Failed to mute.")

    case "set-volume":
        try:
            webos_client.set_volume(sys.argv[2])
            print("Success")
        except:
            print("Failed to set volume.")

    case "close-app":
        try:
            webos_client.close_app(sys.argv[2])
            print("Success")
        except:
            print("Failed to close app.")

    case "get-muted":
        print(webos_client.get_muted())

    case "power-off":
        try:
            webos_client.power_off()
            print("Success")
        except:
            print("Failed to power off.")

    case "power-on":
        try:
            webos_client.power_on()
            print("Success")
        except:
            print("Failed to power on.")

    case "turn-3d-on":
        try:
            webos_client.turn_3d_on()
            print("Success")
        except:
            print("Failed to turn 3D on.")

    case "turn-3d-off":
        try:
            webos_client.turn_3d_off()
            print("Success")
        except:
            print("Failed to turn 3D off.")

    case "is-registered":
        if webos_client.is_registered():
            print("Success")
        else:
            print("Not registered.")

    case "get-key-file-path":
        print(webos_client.get_key_file_path())

    case "get-current-app":
        print(webos_client.get_current_app())

    case "get-software-info":
        print(webos_client.get_software_info())

    case "get-audio-status":
        print(webos_client.get_audio_status())

    case "get-volume":
        print(webos_client.get_volume())


