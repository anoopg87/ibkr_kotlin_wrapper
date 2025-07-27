# IBKR Kotlin Wrapper

Idiomatic, well-documented Kotlin wrapper for the Interactive Brokers Java API (ibapi.jar).

## Features
- Idiomatic Kotlin API for all major IBKR functions
- Lambda-based event/callback handling for all EWrapper events
- Type-safe contract creation using sealed classes (see `ContractFactory.kt`)
- Fully documented with KDoc and usage examples

## Usage
Add the dependency to your build (see below for local publishing):

```kotlin
implementation("com.anooplab.ibwrapper:ibkr-kotlin-wrapper:0.1.0")
```

### Example
```kotlin
val ib = IBApiWrapper()
ib.onTickPrice = { tickerId, field, price, attribs ->
    println("Tick price: $tickerId $field $price $attribs")
}
ib.connect("127.0.0.1", 7497, 0)
val contract = Stock("AAPL", "SMART", "USD")
ib.reqMktData(1, contract, null, false, false, null)
// ...
ib.disconnect()
```

## Publishing
To publish to your local Maven repository:

```sh
./gradlew publishToMavenLocal
```

Or to a local folder:

```sh
./gradlew publish
```

Artifacts will be in `build/repo`.

## License
MIT
