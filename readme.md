```java
@GetMapping("/getUser")
public User getUser(HttpServletRequest request) {
    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        jwt = authHeader.substring(7);
        username = jwtUtil.extractUsername(jwt);
    }
    return userRepository.findFirstByEmail(username);
}

```