CREATE TABLE users(
    id TEXT NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,

    created_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP(3) DEFAULT NULL,

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
)