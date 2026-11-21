# BankAccount-AOP

Petit projet Java qui illustre la programmation orientée aspect (AOP) avec AspectJ autour d'une application de gestion de comptes bancaires.

## Prérequis

- JDK 21
- Maven

## Compilation et exécution

Depuis la racine du projet :

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bank.Main"
```

Le projet utilise le plugin Maven AspectJ pour tisser les aspects à la compilation. `Main` crée deux comptes, effectue un dépôt et tente un retrait. Avec les montants actuellement définis, le retrait dépasse le solde disponible : `PatchRetirerAspect` l'interrompt et lève une `IllegalArgumentException`.

## Aspects

### `LoggingAspect`

Intercepte les exécutions de méthodes dans `com.bank.metier` avec le pointcut :

```java
execution(* com.bank.metier.*.*(..))
```

L'advice `@Around` journalise la signature avant et après l'appel et mesure sa durée. Les événements sont écrits dans `log.xml`, créé dans le répertoire courant au lancement.

Le pointcut s'applique à toutes les classes du package `com.bank.metier`, y compris `Account`. Les appels imbriqués (par exemple à `getSolde()` pendant un retrait) sont donc aussi journalisés.

### `PatchRetirerAspect`

Intercepte `IMetierImpl.retirer(code, sold)` avant l'exécution du retrait. Si le solde du compte est inférieur au montant demandé, l'aspect lève une `IllegalArgumentException` et le retrait n'a pas lieu.

## Structure du projet

```text
src/main/java/com/bank/
├── Main.java
├── aspects/
│   ├── LoggingAspect.java
│   └── PatchRetirerAspect.java
└── metier/
    ├── Account.java
    ├── IMetier.java
    └── IMetierImpl.java
```

## Limites actuelles

- `PatchRetirerAspect` ne traite pas explicitement un compte inexistant : il peut alors provoquer une `NullPointerException`.
- Les montants nuls ou négatifs ne sont pas refusés par l'aspect.
- La vérification du solde et le retrait ne sont pas atomiques en cas d'accès concurrent.
