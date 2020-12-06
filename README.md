# posprinter

SDK For JVZ3

## Get Started

Add jitpack as a source of dependencies from your main project `gradle.build` file

```gradle
allprojects {
    repositories {
        // jit pack repo
        maven { url 'https://jitpack.io' }
        // other repos
        google()
        jcenter()
    }
}
```

Then to your module build file `build.gradle of module` add printer dependency

```gradle
dependencies {
    // ...
    implementation 'com.github.fahamutech:posprinter:v0.1.2'
    // ...
}
```

## Initiate Printer SDK

To print you must initiate printer SDK when application start.

```kotlin
override fun onCreate(bundle: Bundle){
    super.onCreate();
    
    // initiate printer sdk
    JZV3Printer.getInstance().init(this);
    
    // ..
}
```
## Print 

Your can create a function to print like the following code.

```kotlin

import com.fahamutech.posprinter.JZV3Printer
import com.fahamutech.posprinter.JZV3PrinterCallback
import com.fahamutech.posprinter.PrinterError
import com.vanstone.trans.api.PrinterApi

fun print(qr: String, data: String, context: Context) {
        if (Build.MODEL.trim { it <= ' ' } == "JZV3") {
            JZV3Printer.getInstance().print(applicationContext, object : JZV3PrinterCallback {
                override fun onReadToPrint() {
                    PrinterApi.PrnClrBuff_Api()
                    if (qr != null && qr.isNotEmpty() && qr.isNotBlank()) {
                        PrinterApi.printAddQrCode_Api(1, 150, qr)
                    }
                    PrinterApi.printFeedLine_Api(10)
                    PrinterApi.PrnFontSet_Api(24, 24, 0)
                    // PrinterApi.PrnSetGray_Api(30);
                    PrinterApi.PrnLineSpaceSet_Api(5.toShort(), 0)
                    PrinterApi.PrnStr_Api(data)
                }

                override fun onError(printerError: PrinterError) {
                    Toast.make(context,"FAILS TO PRINT", Toast.LENGHT_SHORT).show()
                }

                override fun onSuccess() {
                   Toast.make(context,"Done printing", Toast.LENGHT_SHORT).show()
                }
            })
        } else {
            Toast.make(context,"printer not supported",Toast.LENGHT_SHORT).show()
        }
    }

```

## Note

All examples are in kotlin but you can rewrite them in java.

For device to work propery without extra effort use `classpath 'com.android.tools.build:gradle:3.5.0'` or below

