### List of mobile devices available for testing on BrowserStack test automation service

Register in BrowserStack. Free trial available.
`https://www.browserstack.com/`

Navigate to Dashboard and copy API username and access key
`https://app-automate.browserstack.com/dashboard/v2/getting-started`

Create `browser.xml` file in `app/src/main/res/values/browser.xml` and insert keys:

```
<resources>
    <string name="username">USERNAME</string>
    <string name="password">PASSWORD</string>
</resources>
```

### Tech stack
- Kotlin
- Jetpack Compose
- Retrofit 2, okhttp 3
- Coil as image processing